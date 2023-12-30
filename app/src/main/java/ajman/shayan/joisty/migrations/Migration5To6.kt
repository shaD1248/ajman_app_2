package ajman.shayan.joisty.migrations

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

class Migration5To6 : Migration(5, 6) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE joist_design DROP COLUMN updated_at;")
        database.execSQL("ALTER TABLE joist_design ADD COLUMN updated_at TEXT NOT NULL DEFAULT '2024-01-01T00:00:00.000000';")
    }
}