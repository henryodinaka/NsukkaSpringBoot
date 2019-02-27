package com.leo.henry.messenger;

import com.leo.henry.messenger.resources.MessageResources;
import com.leo.henry.messenger.resources.UserResource;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig
{
    public JerseyConfig()
    {
        packages("com.leo.henry.messenger.resources","com.leo.henry.messenger.exceptions","com.leo.henry.messenger.filter");
//        register(UserResource.class);
//        register(MessageResources.class);
    }
}