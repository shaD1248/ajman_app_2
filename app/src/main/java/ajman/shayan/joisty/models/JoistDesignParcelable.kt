package ajman.shayan.joisty.models

import ajman.shayan.joisty.entities.JoistDesign
import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.RequiresApi

class JoistDesignParcelable(var joistDesign: JoistDesign): Parcelable {
    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(p0: Parcel, p1: Int) {
    }

    companion object CREATOR : Parcelable.Creator<JoistDesign> {
        @RequiresApi(Build.VERSION_CODES.O)
        override fun createFromParcel(parcel: Parcel): JoistDesign {
            // Read the parcel and create an instance of JoistDesign
            return JoistDesign(600.0)
        }

        override fun newArray(size: Int): Array<JoistDesign?> {
            return arrayOfNulls(size)
        }
    }

}