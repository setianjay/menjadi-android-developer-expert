package com.setianjay.watchme.core.constants

object RemoteConst {

    /* BASE URL */
    const val BASE_URL = "https://api.themoviedb.org/3/"

    /* ERROR CODE */
    const val ERR_CODE_API = -1
    const val ERR_CODE_EMPTY = -2

    /* IMAGE URL */
    const val IMAGE_URL_ORIGINAL = "https://image.tmdb.org/t/p/original"

    /* TYPE */
    const val POPULAR = "popular" // movie or tv can use this value
    const val NOW_PLAYING = "now_playing" // only for movie can use this value
    const val ON_THE_AIR = "on_the_air" // only for tv can use this value
}