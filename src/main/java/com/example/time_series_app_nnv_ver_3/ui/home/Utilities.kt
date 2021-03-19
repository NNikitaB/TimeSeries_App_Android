package com.example.time_series_app_nnv_ver_3.ui.home

import android.net.Uri
import java.io.BufferedReader
import java.io.File
import java.io.InputStream
import java.io.InputStreamReader
import java.net.URL

class Utilities {
    private val str: Array<String> =arrayOf(
        "<TICKER>,<PER>,<DATE>,<TIME>,<CLOSE>",
        "GAZP,60,20210318,110000,229.3000000",
        "GAZP,60,20210318,120000,227.5800000",
        "GAZP,60,20210318,130000,226.0500000",
        "GAZP,60,20210318,140000,225.6600000",
        "GAZP,60,20210318,150000,227.0800000",
        "GAZP,60,20210318,160000,227.8300000",
        "GAZP,60,20210318,170000,227.9900000",
        "GAZP,60,20210318,180000,227.8800000",
        "GAZP,60,20210318,190000,227.5500000",
        "GAZP,60,20210318,200000,226.5700000",
        "GAZP,60,20210318,210000,226.8600000",
        "GAZP,60,20210318,220000,225.4700000",
        "GAZP,60,20210318,230000,224.2100000",
        "GAZP,60,20210319,000000,224.3500000"
    )

    fun Parse(filename: URL):MutableList<Double>{
        /** Format input file <TICKER>,<PER>,<DATE>,<TIME>,<CLOSE>
         * Format output array<COST_CLOSE>**/
        //var arr:List<Double> = listOf(0.0)
        val arr:MutableList<Double> = mutableListOf()
        //val inputStream = InputStream(filename)
        //val file = filename.toFile()
        //val bufferedReader: BufferedReader =file.bufferedReader()
        //val bufferedReader: BufferedReader = File(filename).bufferedReader()


        //val state = bufferedReader.useLines {  Lines ->Lines.forEach { arr.add(it.split(",")[4].toDouble()) } }
        return arr
    }


    fun Parse(str:Array<String> = this.str):MutableList<Double>{
        val itt =str.iterator()
        itt.next()
        val arr:MutableList<Double> = mutableListOf()
        while (itt.hasNext()){
            arr.add(itt.next().split(",")[4].toDouble())
        }
        return arr
    }

//    fun MA5(MutableList<Double>):MutableList<Double>{
//
//    }

    class SlidingSequence<out T>(val source: Iterable<T>,
                                 val slideSize: Int,
                                 val slideStep: Int) : Sequence<List<T>> {
        override fun iterator(): Iterator<List<T>> = object : AbstractIterator<List<T>>() {
            private val iterator = if (slideSize > 0) source.iterator() else emptyList<T>().iterator()
            private var buffer = listOf<T>()

            override fun computeNext() = when {
                iterator.hasNext() -> {
                    buffer = buffer.drop(slideStep).let {
                        it + iterator.asSequence().take(slideSize - it.size)
                    }
                    setNext(buffer)
                }
                else -> done()
            }
        }
    }

    fun <T> Iterable<T>.windowed(size: Int,
                                 step: Int = 1): Sequence<List<T>> {
        return SlidingSequence(this, size, step)
    }
}



