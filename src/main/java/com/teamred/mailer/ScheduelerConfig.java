package com.teamred.mailer;

import org.quartz.*;

import java.util.Date;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

public class ScheduelerConfig {

    SchedulerFactory schedFact = new org.quartz.impl.StdSchedulerFactory();

    Scheduler sched;

    {
        try {
            sched = schedFact.getScheduler();
            sched.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    // define the job and tie it to our Mailer class
    JobDetail job = newJob(Mailer.class)
            .withIdentity("RedTeamReport", "group1")
            .build();

    // Trigger the job to run at 19 12 2021, and then every day
    Trigger trigger = newTrigger()
            .withIdentity("myTrigger", "group1")
            .startAt(new Date("19 12 2021"))
            .withSchedule(simpleSchedule()
                    .withIntervalInHours(24)
                    .repeatForever())
            .build();
}
