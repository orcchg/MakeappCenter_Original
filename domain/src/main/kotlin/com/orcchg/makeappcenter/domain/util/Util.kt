package com.orcchg.makeappcenter.domain.util

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

    fun <T, R> mapItems(source: Collection<T>, transformer: (T) -> R): List<R> {
        checkNotNull(source, "source == null")
        checkNotNull(transformer, "transformer == null")
        val result = arrayListOf<R>()
        source.forEach {
            result.add(transformer.invoke(it))
        }

        return result
    }

    fun <T> firstItem(source: List<T>?): T? {
        return if (source != null && !source.isEmpty()) source[0] else null
    }

    fun <T> filter(source: List<T>?, condition: (T) -> Boolean): List<T>? {
        checkNotNull(source, "source == null")
        checkNotNull(condition, "condition == null")
        val result = arrayListOf<T>()
        source!!.forEach {
            if (condition.invoke(it)) {
                result.add(it)
            }
        }
        return result
    }

    fun <T, R> firstItem(source: List<T>?, transformer: (T) -> R): R? {
        checkNotNull(transformer, "transformer == null")
        return if (source != null && !source.isEmpty()) transformer.invoke(source[0]) else null
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

    fun <T, R> fold(initialValue: R, source: Collection<T>, accumulator: (R, T) -> R): R {
        checkNotNull(source, "source == null")
        checkNotNull(accumulator, "accumulator == null")
        var result = initialValue
        source.forEach {
            result = accumulator.invoke(result, it)
        }
        return result
    }
}
