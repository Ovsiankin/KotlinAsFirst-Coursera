@file:Suppress("UNUSED_PARAMETER")
package lesson2.task1

import lesson1.task1.discriminant
import lesson1.task1.sqr
import kotlin.math.max
import kotlin.math.sqrt

/**
 * Пример
 *
 * Найти число корней квадратного уравнения ax^2 + bx + c = 0
 */
fun quadraticRootNumber(a: Double, b: Double, c: Double): Int {
    val discriminant = discriminant(a, b, c)
    return when {
        discriminant > 0.0 -> 2
        discriminant == 0.0 -> 1
        else -> 0
    }
}

/**
 * Пример
 *
 * Получить строковую нотацию для оценки по пятибалльной системе
 */
fun gradeNotation(grade: Int): String = when (grade) {
    5 -> "отлично"
    4 -> "хорошо"
    3 -> "удовлетворительно"
    2 -> "неудовлетворительно"
    else -> "несуществующая оценка $grade"
}

/**
 * Пример
 *
 * Найти наименьший корень биквадратного уравнения ax^4 + bx^2 + c = 0
 */
fun minBiRoot(a: Double, b: Double, c: Double): Double {
    // 1: в главной ветке if выполняется НЕСКОЛЬКО операторов
    if (a == 0.0) {
        if (b == 0.0) return Double.NaN // ... и ничего больше не делать
        val bc = -c / b
        if (bc < 0.0) return Double.NaN // ... и ничего больше не делать
        return -sqrt(bc)
        // Дальше функция при a == 0.0 не идёт
    }
    val d = discriminant(a, b, c)   // 2
    if (d < 0.0) return Double.NaN  // 3
    // 4
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    val y3 = max(y1, y2)       // 5
    if (y3 < 0.0) return Double.NaN // 6
    return -sqrt(y3)           // 7
}

/**
 * Простая
 *
 * Мой возраст. Для заданного 0 < n < 200, рассматриваемого как возраст человека,
 * вернуть строку вида: «21 год», «32 года», «12 лет».
 */
fun ageDescription(age: Int): String{
    var lastChar: Int = age % 10

    return when (age) {
        1 -> "$age год"
        in 2..4 -> "$age года"
        in 5..20 -> "$age лет"
        in 105..120 -> "$age лет"

        else -> when (lastChar) {
            1 -> "$age год"
            in 2..4 -> "$age года"
            else -> "$age лет"
        }
    }
}

/**
 * Простая
 *
 * Путник двигался t1 часов со скоростью v1 км/час, затем t2 часов — со скоростью v2 км/час
 * и t3 часов — со скоростью v3 км/час.
 * Определить, за какое время он одолел первую половину пути?
 */
fun timeForHalfWay(t1: Double, v1: Double,
                   t2: Double, v2: Double,
                   t3: Double, v3: Double): Double {
    val s1: Double = t1 * v1
    val s2: Double = t2 * v2
    val s3: Double = t3 * v3
    var tHalf: Double = 0.0
    var sHalf: Double = (s1 + s2 + s3) / 2

    if (sHalf < s1) {
        tHalf = sHalf / v1
    } else if (sHalf > s1 && sHalf < s1 + s2) {
        tHalf = t1 + (sHalf - s1) / v2
    } else tHalf = t1 + t2 + (sHalf - (s1 + s2)) / v3
    return tHalf
}

/**
 * Простая
 *
 * Нa шахматной доске стоят черный король и две белые ладьи (ладья бьет по горизонтали и вертикали).
 * Определить, не находится ли король под боем, а если есть угроза, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от первой ладьи, 2, если только от второй ладьи,
 * и 3, если угроза от обеих ладей.
 * Считать, что ладьи не могут загораживать друг друга
 */
fun whichRookThreatens(kingX: Int, kingY: Int,
                       rookX1: Int, rookY1: Int,
                       rookX2: Int, rookY2: Int): Int {
    if (kingX == rookX1 && kingX == rookX2) {
        return 3
    } else if (kingY == rookY1 && kingY == rookY2) {
        return 3
    } else if (kingX == rookX1 && kingY == rookY2) {
        return 3
    } else if (kingX == rookX2 && kingY == rookY1){
        return 3
    } else if (kingX == rookX1){
        return 1
    } else if (kingX == rookX2){
        return 2
    } else if (kingY == rookY1){
        return 1
    } else if (kingY == rookY2){
        return 2
    } else return 0
}

/**
 * Простая
 *
 * На шахматной доске стоят черный король и белые ладья и слон
 * (ладья бьет по горизонтали и вертикали, слон — по диагоналям).
 * Проверить, есть ли угроза королю и если есть, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от ладьи, 2, если только от слона,
 * и 3, если угроза есть и от ладьи и от слона.
 * Считать, что ладья и слон не могут загораживать друг друга.
 */
fun rookOrBishopThreatens(kingX: Int, kingY: Int,
                          rookX: Int, rookY: Int,
                          bishopX: Int, bishopY: Int): Int{


    if ((kingX - bishopX == kingY - bishopY || kingX - bishopX == -(kingY - bishopY)) && (kingX == rookX || kingY == rookY)) {
        return 3
    } else if (kingX - bishopX == kingY - bishopY || kingX - bishopX == -(kingY - bishopY)) {
        return 2
    } else if (kingX == rookX || kingY == rookY) {
        return 1
    } else return 0
}

/**
 * Простая
 *
 * Треугольник задан длинами своих сторон a, b, c.
 * Проверить, является ли данный треугольник остроугольным (вернуть 0),
 * прямоугольным (вернуть 1) или тупоугольным (вернуть 2).
 * Если такой треугольник не существует, вернуть -1.
 */
fun triangleKind(a: Double, b: Double, c: Double): Int {
    var angleA: Double = (sqr(a) + sqr(c) - sqr(b)) / 2 * a * c
    var angleB: Double = (sqr(b) + sqr(c) - sqr(a)) / 2 * b * c
    var angleC: Double = (sqr(a) + sqr(b) - sqr(c)) / 2 * a * b

    if (a < b + c && b < a + c && c < a + b) {
        return when {
            angleA == 0.0 || angleB == 0.0 || angleC == 0.0 -> 1
            angleA > 0 && angleB > 0 && angleC > 0 -> 0
            else -> 2
        }
    } else return -1
}

/**
 * Средняя
 *
 * Даны четыре точки на одной прямой: A, B, C и D.
 * Координаты точек a, b, c, d соответственно, b >= a, d >= c.
 * Найти длину пересечения отрезков AB и CD.
 * Если пересечения нет, вернуть -1.
 */
fun segmentLength(a: Int, b: Int, c: Int, d: Int): Int {
    if (a < c && b > c) {
        return b - c
    } else if (a > c && b < d) {
        return b - a
    } else if (a < d && b > d) {
        return d - a
    } else if (b == c || a == d) {
        return 0
    } else return -1
}
