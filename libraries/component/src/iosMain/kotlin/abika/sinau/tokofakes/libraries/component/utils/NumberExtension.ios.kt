package abika.sinau.tokofakes.libraries.component.utils

import platform.Foundation.NSNumberFormatter

actual val Double.toRupiah: String get() {
    val numberFormat = NSNumberFormatter()
    numberFormat.locale = NSLocale("id_ID")
    numberFormat.numberStyle = 2u
    return numberFormat.stringFromNumber(NSNumber(this)).orEmpty()
}