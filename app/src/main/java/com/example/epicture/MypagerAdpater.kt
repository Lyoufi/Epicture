package com.example.epicture

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class MypagerAdpater (fm : FragmentManager) : FragmentPagerAdapter (fm) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                FragmentOne()
            }
            1 -> FragmentTwo()
                else -> {
                    return FragmentTree()
                }
        }
    }
    //La premiere méthode set les tabs

    override fun getCount(): Int {
        return 3
        //  Cette méthode return 3 tabs
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Home"
            1 -> "Feed"
            else -> {
                return "Profile"
            }
        }

    }

    //Enfin la derniere méthode donne un titre à chaque tab
}