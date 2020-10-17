package com.impact.assistantapp.data.db.plan

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.impact.assistantapp.data.model.Plan

@Database(entities = arrayOf(Plan::class), version = 1)
abstract class PlanDb: RoomDatabase() {
    abstract fun planDao(): PlanDao

    companion object {
        var INSTANCE: PlanDb? = null

        fun getInstance(context: Context): PlanDb {

            if (INSTANCE == null) {
                INSTANCE =
                    Room.databaseBuilder(context.applicationContext, PlanDb::class.java, "Plan.db")
                        .build()
            }
            return INSTANCE as PlanDb;
        }
        
    }

    /*fun getData(data: PlanDb): Flowable<LiveData<MutableList<Plan>>> {
        return Flowable.create({emitter ->
            planDao().getAllPlans()
        })
    }*/


}