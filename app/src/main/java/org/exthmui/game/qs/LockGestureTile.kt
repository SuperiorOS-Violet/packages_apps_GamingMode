/*
 * Copyright (C) 2020 The exTHmUI Open Source Project
 * Copyright (C) 2021 AOSP-Krypton Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.exthmui.game.qs

import android.content.Context
import android.os.RemoteException
import android.os.ServiceManager
import android.util.Log

import com.android.internal.statusbar.IStatusBarService

import org.exthmui.game.R

class LockGestureTile(context: Context?) :
    TileBase(context, R.string.qs_lock_gesture, R.drawable.ic_qs_disable_gesture) {

    private val statusBarService: IStatusBarService

    init {
        statusBarService = IStatusBarService.Stub.asInterface(
            ServiceManager.getService(Context.STATUS_BAR_SERVICE)
        )
    }

    override fun handleClick(isSelected: Boolean) {
        super.handleClick(isSelected)
        try {
            statusBarService.setBlockedGesturalNavigation(isSelected)
        } catch (e: RemoteException) {
            Log.e(TAG, "Failed to toggle gesture")
        }
    }

    companion object {
        private const val TAG = "LockGestureTile"
    }
}