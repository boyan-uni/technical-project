package com.boyan.mapper;

import com.boyan.pojo.Schedule;

import java.util.List;

/**
 * Mapper 接口
 * @author boyan
 */

public interface ScheduleMapper {

    /**
     * Retrieve a list of all schedules.
     *
     * @return List of Schedule objects.
     */
    List<Schedule> list();

    /**
     * Insert a new schedule.
     *
     * @param schedule Schedule object to be inserted.
     * @return int 1 if insert was successful, 0 otherwise.
     */
    int insert(Schedule schedule);

    /**
     * Delete a schedule by its ID.
     *
     * @param id ID of the schedule to be deleted.
     * @return int 1 if delete was successful, 0 otherwise.
     */
    int deleteById(Integer id);

    /**
     * Update an existing schedule.
     *
     * @param schedule Schedule object with updated details.
     * @return int 1 if update was successful, 0 otherwise.
     */
    int update(Schedule schedule);
}
