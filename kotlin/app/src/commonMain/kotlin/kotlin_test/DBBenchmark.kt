package kotlin_test

object DBBenchmark

object DB {

    fun() : TestDB

    const val TABLE_NAME = "USERS"
    const val COLUMN_ID = "ID"
    const val COLUMN_LOGIN = "LOGIN"
    const val COLUMN_EMAIL = "EMAIL"
    const val COLUMN_NAME = "NAME"
    const val COLUMN_AGE = "AGE"

    const val SQL_CREATE_TABLE = "CREATE TABLE $TABLE_NAME (" +
            "$COLUMN_ID INTEGER PRIMARY KEY, " +
            "$COLUMN_LOGIN TEXT UNIQUE, " +
            "$COLUMN_EMAIL TEXT UNIQUE, " +
            "$COLUMN_NAME TEXT NOT NULL, " +
            "$COLUMN_AGE INTEGER NOT NULL CHECK ($COLUMN_AGE > 0 AND $COLUMN_AGE < 150))"

    const val SQL_DROP_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"
}

