package com.setianjay.watchme.core.utils

object DataUtil {

    /**
     * list of map genres (hardcode)
     *
     * @return [Map]        list of map genre
     * */
    private fun genres(): Map<Int, String> {
        return mapOf(
            28 to "Action",
            12 to "Adventure",
            16 to "Animation",
            35 to "Comedy",
            80 to "Crime",
            99 to "Documentary",
            18 to "Drama",
            10751 to "Family",
            14 to "Fantasy",
            36 to "History",
            27 to "Horror",
            10402 to "Music",
            9648 to "Mystery",
            10749 to "Romance",
            878 to "Science Fiction",
            10770 to "Tv Movie",
            53 to "Thriller",
            10752 to "War",
            37 to "Western",
            10759 to "Action & Adventure",
            10762 to "Kids",
            10763 to "News",
            10764 to "Reality",
            10765 to "Sci-Fi & Fantasy",
            10766 to "Soap",
            10767 to "Talk",
            10768 to "War & Politics",
        )
    }

    /**
     * get genre based on list genre id
     *
     * @param   genreIds        list of genre id
     * @return  [String]        genres string, eg. Adventure, Drama, Action or empty string ""
     * */
    fun getGenreByListId(genreIds: List<Int>): String {
        val genreStrBuilder = StringBuilder()
        val genres = genres()

        if(genreIds.isEmpty()){
            return "No genre yet"
        }

        loopI@ for (i in genreIds.indices) {
            loopJ@ for ((id, value) in genres) {
                /* id genre same with id */
                if (genreIds[i] == id) {
                    if (i == genreIds.size - 1) {
                        genreStrBuilder.append(value)
                    } else {
                        genreStrBuilder.append("$value, ")
                    }
                    break@loopJ
                } else {
                    continue
                }
            }
        }

        return genreStrBuilder.toString()
    }
}