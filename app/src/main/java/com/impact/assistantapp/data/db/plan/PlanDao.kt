package com.impact.assistantapp.data.db.plan

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.impact.assistantapp.data.model.Plan
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface PlanDao {

    @Query("SELECT * FROM plan_table")
    fun getAllPlans() : Flowable<MutableList<Plan>>

    @Insert
    fun addPlan(plan: Plan): Completable

    @Update(onConflict = REPLACE)
    fun updatePlan(plan: Plan)

    @Delete
    fun deletePlan(plan: Plan?)

}