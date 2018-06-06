package com.foreceipt.mybooks

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Created by lukelin on 2018-06-06.
 */

open class SingleNovel : RealmObject() {
    @PrimaryKey
    var id: Int = 0
    var url: String? = null
    var content: String? = null
}
