package tn.oussama.mp_android_bonita

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat.finishAffinity
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_list_process.*
import kotlinx.android.synthetic.main.fragment_side_menu.*
import kotlinx.coroutines.launch
import tn.oussama.mp_android_bonita.framework.network.retrofit.UserRetrofit
import tn.oussama.mp_android_bonita.framework.utils.clearUserSession

class SideMenuFragment : Fragment(R.layout.fragment_side_menu) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navView.setNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.allProcessesITEM -> {
                    if(activity  !is ListProcessesActivity) {
                        val intent = Intent(activity, ListProcessesActivity::class.java).apply {
                            putExtra("USER_CREDENTIALS", "NOTHING")
                        }
                        startActivity(intent)
                    }
                }

                R.id.logoutITEM -> {
                    viewLifecycleOwner.lifecycleScope.launch {
                        finishAffinity(requireActivity())
                        val intent = Intent(activity, MainActivity::class.java).apply {
                            putExtra("LOGOUT", true)
                        }
                        startActivity(intent)
                    }
                }
            }
            true
        }
    }

}