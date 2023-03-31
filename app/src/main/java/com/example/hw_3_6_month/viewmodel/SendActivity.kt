import android.net.Uri
import android.view.LayoutInflater
import androidx.recyclerview.widget.GridLayoutManager
import com.example.hw_3_6_month.databinding.ActivitySendBinding
import com.example.hw_3_6_month.viewmodel.BaseActivity
import com.example.hw_3_6_month.viewmodel.MainActivity
import com.example.hw_3_6_month.viewmodel.adapter.AdapterSend

class SendActivity : BaseActivity<ActivitySendBinding>() {

    private val adapter by lazy { AdapterSend() }

    override fun inflateVB(inflater: LayoutInflater): ActivitySendBinding {
        return ActivitySendBinding.inflate(inflater)
    }

    override fun initListener() {
        intent.getParcelableArrayListExtra<Uri>(MainActivity.KEY_IMG)?.let {
            adapter.addImages(it)
        }
    }

    override fun initView() {
        binding.selectedRecycler.apply {
            layoutManager = GridLayoutManager(this@SendActivity, 3)
            adapter = this@SendActivity.adapter
        }
    }
}
