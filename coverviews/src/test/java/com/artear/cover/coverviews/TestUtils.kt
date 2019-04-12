package com.artear.cover.coverviews

import java.nio.charset.Charset

class TestUtils {

    companion object {

        fun log(message : String){
            System.out.println("[ArtearTest] - $message")
        }

        /**
         * @param loader loader needed to get the file
         * @param folder Folder where the files are stored
         * @param file   filename for this test
         * @return file content as string
         */
        fun loadJSONFromAsset(loader: ClassLoader, folder: String, file: String): String? {
            var json: String? = null

            val builder = StringBuilder()
            builder.append(folder)
            builder.append("/")
            builder.append(file)
            builder.append(".json")
            try {
                val `is` = loader.getResourceAsStream(builder.toString())
                val size = `is`.available()
                val buffer = ByteArray(size)
                `is`.read(buffer)
                `is`.close()
                json = String(buffer, Charset.forName("UTF-8"))
            } catch (ex: Exception) {
                ex.printStackTrace()
            }

            return json
        }
    }

}
