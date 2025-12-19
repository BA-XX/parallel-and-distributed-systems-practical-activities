package site.aloui.practicalactivity1.events;

import java.util.Date;

public record PageEvent(String name,
                        String user,
                        Date date,
                        long duration)
{
}
