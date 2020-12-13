package com.supinfo.andm.utils

import android.content.Context

class FileHelper {
    companion object {
        fun getTextFromResources(context: Context?, resourceId: Int): String {
            // use allow to manage the streams closure
            return context!!.resources.openRawResource(resourceId).use {
                it.bufferedReader().use {
                    it.readText()
                }
            }
        }

        fun getTextFromAssets(context: Context?, fileName: String): String {
            // use allow to manage the streams closure
            return context!!.assets.open(fileName).use {
                it.bufferedReader().use {
                    it.readText()
                }
            }
        }
    }
}