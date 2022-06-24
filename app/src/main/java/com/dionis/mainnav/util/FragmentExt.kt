package com.dionis.mainnav.util

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

fun Fragment.navTo(@IdRes dest: Int, args: Bundle) = findNavController().navigate(dest, args)