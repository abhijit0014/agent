

```
        list = List.of(

                new JobEntry(Job.ENG_HOURLY_LOAD)
                        .agent(agentName)
                        .intervalUnit(IntervalUnit.Hourly)
                        .jobParts(List.of("1","2","3","4","5"))
                        .task(EngHourlyLoadTask.class),

                new JobEntry(Job.SUB_HOURLY_LOAD)
                        .agent(agentName)
                        .intervalUnit(IntervalUnit.Hourly)
                        .jobParts(List.of("1","2","3","4","5"))
                        .task(SubHourlyLoadTask.class)
        );

```
