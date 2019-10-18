@file:Suppress("UNUSED_PARAMETER")

package lesson2.task2

import com.sun.org.apache.xpath.internal.operations.*
import lesson1.task1.sqr
import kotlin.math.*

fun sqr(x: Double) = x * x

/**
 * Пример
 *
 * Лежит ли точка (x, y) внутри окружности с центром в (x0, y0) и радиусом r?
 */
fun pointInsideCircle(x: Double, y: Double, x0: Double, y0: Double, r: Double) =
        sqr(x - x0) + sqr(y - y0) <= sqr(r)

/**
 * Простая
 *
 * Четырехзначное число назовем счастливым, если сумма первых двух ее цифр равна сумме двух последних.
 * Определить, счастливое ли заданное число, вернуть true, если это так.
 */
fun isNumberHappy(number: Int): Boolean {
    val fourthChar: Int = number % 10
    val thirdChar: Int = (number / 10) % 10
    val secondChar: Int = (number / 100) % 10
    val firstChar: Int = number / 1000
    return firstChar + secondChar == thirdChar + fourthChar
}

/**
 * Простая
 *
 * На шахматной доске стоят два ферзя (ферзь бьет по вертикали, горизонтали и диагоналям).
 * Определить, угрожают ли они друг другу. Вернуть true, если угрожают.
 * Считать, что ферзи не могут загораживать друг друга.
 */
fun queenThreatens(x1: Int, y1: Int, x2: Int, y2: Int): Boolean =
        (x1 == x2 || y1 == y2) || (abs(x1 - x2) == abs(y1 - y2))


/**
 * Простая
 *
 * Дан номер месяца (от 1 до 12 включительно) и год (положительный).
 * Вернуть число дней в этом месяце этого года по григорианскому календарю.
 */
fun daysInMonth(month: Int, year: Int): Int {

    var monthLength: Int = 0


    when (month) {
        1, 3, 5, 7, 8, 10, 12 -> monthLength = 31
        4, 6, 9, 11 -> monthLength = 30
        2 -> monthLength = 28
        else -> "error"
    }
    if (year in 1920..2100 step 4 && month == 2) monthLength = 29
    return monthLength
}

/**
 * Средняя
 *
 * Проверить, лежит ли окружность с центром в (x1, y1) и радиусом r1 целиком внутри
 * окружности с центром в (x2, y2) и радиусом r2.
 * Вернуть true, если утверждение верно
 */

/*
Сначала проверить, находится ли центр круга1 в круге2
Вычислить максимальный возможный радиус (z) по одной оси или треугольнику abc
Если z <= r1 и centerInside - true

*/

fun circleInside(x1: Double, y1: Double, r1: Double,
                 x2: Double, y2: Double, r2: Double): Boolean {
    var a: Double = 0.0
    var b: Double = 0.0
    var c: Double = 0.0
    var z: Double = 0.0

    fun centerInside(centerInside: Boolean): Boolean = sqr(x1 - x2) + sqr(y1 - y2) <= sqr(r2)

    if (x1 == x2 && y1 == y2) {
        z = r2
    } else if (x1 == x2 && y1 != y2) {
        z = r2 - abs(y1 - y2)
    } else if (x1 != x2 && y1 == y2) {
        z = r2 - abs(x1 - x2)
    } else if (x1 != x2 && y1 != y2) {
        a = abs(x1 - x2)
        b = abs(y1 - y2)
        c = sqrt(sqr(a) + sqr(b))
        z = r2 - c
    }
    return centerInside(true) && r1 <= z
}

/**
 * Средняя
 *
 * Определить, пройдет ли кирпич со сторонами а, b, c сквозь прямоугольное отверстие в стене со сторонами r и s.
 * Стороны отверстия должны быть параллельны граням кирпича.
 * Считать, что совпадения длин сторон достаточно для прохождения кирпича, т.е., например,
 * кирпич 4 х 4 х 4 пройдёт через отверстие 4 х 4.
 * Вернуть true, если кирпич пройдёт
 */
fun brickPasses(a: Int, b: Int, c: Int, r: Int, s: Int): Boolean {

    var b1 = when {
        a <= r && (b <= s || c <= s) -> true
        b <= r && (a <= s || c <= s) -> true
        c <= r && (b <= s || a <= s) -> true
        a <= s && (b <= r || c <= r) -> true
        b <= s && (a <= r || c <= r) -> true
        c <= s && (a <= r || b <= r) -> true
        else -> false
    }
    return b1
}
