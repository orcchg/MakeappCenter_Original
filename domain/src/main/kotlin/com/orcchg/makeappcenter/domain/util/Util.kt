package com.orcchg.makeappcenter.domain.util

import io.reactivex.functions.BiFunction

object Util {

    fun <I, T : Collection<I>> checkNotEmpty(reference: T?, errorMessage: Any?): T {
        if (reference == null) throw NullPointerException(errorMessage.toString())
        if (reference.isEmpty()) throw IllegalArgumentException(errorMessage.toString())
        return reference
    }

    fun checkNotBlank(reference: String?, errorMessage: Any?): String {
        if (reference == null) throw NullPointerException(errorMessage.toString())
        if (reference.isEmpty()) throw IllegalArgumentException(errorMessage.toString())
        return reference
    }

    fun <T> checkNotNull(reference: T?, errorMessage: Any?): T {
        if (reference == null) {
            throw NullPointerException(errorMessage.toString())
        }
        return reference
    }

    fun <T, R> mapItems(source: Collection<T>, transformer: Function<T, R>): List<R> {
        checkNotNull(source, "source == null")
        checkNotNull(transformer, "transformer == null")
        val result = arrayListOf<R>()
        source.forEach {
            result.add(transformer.apply(it))
        }

        return result
    }

    fun <T> firstItem(source: List<T>?): T? {
        return if (source != null && !source.isEmpty()) source[0] else null
    }

    fun <T> filter(source: List<T>?, condition: Function<T, Boolean>): List<T>? {
        checkNotNull(source, "source == null")
        checkNotNull(condition, "condition == null")
        val result = arrayListOf<T>()
        source!!.forEach {
            if (condition.apply(it)) {
                result.add(it)
            }
        }
        return result
    }

    fun <T, R> firstItem(source: List<T>?, transformer: Function<T, R>): R? {
        checkNotNull(transformer, "transformer == null")
        return if (source != null && !source.isEmpty()) transformer.apply(source[0]) else null
    }

    fun <T> minItem(source: Collection<T>, defaultValue: T, comparator: Comparator<T>): T {
        checkNotNull(source, "source == null")
        checkNotNull(comparator, "comparator == null")
        if (source.isEmpty()) {
            return defaultValue
        }

        var minItem: T? = null
        for (item in source) {
            if (minItem == null) {
                minItem = item
            } else {
                if (comparator.compare(minItem, item) >= 0) {
                    minItem = item
                }
            }
        }
        return minItem!!
    }

    fun <T, R> fold(initialValue: R, source: Collection<T>, accumulator: BiFunction<R, T, R>): R {
        checkNotNull(source, "source == null")
        checkNotNull(accumulator, "accumulator == null")
        var result = initialValue
        source.forEach {
            result = accumulator.apply(result, it)
        }
        return result
    }
}
