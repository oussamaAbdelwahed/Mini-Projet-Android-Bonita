package tn.oussama.mp_android_bonita

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.app.ActivityCompat.finishAffinity
import kotlinx.android.synthetic.main.activity_list_process.*
import kotlinx.android.synthetic.main.fragment_side_menu.*


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
                    finishAffinity(requireActivity())
                    val intent = Intent(activity, MainActivity::class.java).apply {
                        putExtra("USER_CREDENTIALS", "NOTHING")
                    }
                    startActivity(intent)
                }
            }
            true
        }
    }

}