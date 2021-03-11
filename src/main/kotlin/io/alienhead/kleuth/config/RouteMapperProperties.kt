package io.alienhead.kleuth.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import org.springframework.boot.context.properties.NestedConfigurationProperty

@ConfigurationProperties(prefix = "route.mapper")
@ConstructorBinding
class RouteMapperProperties(
    /**
     *
     */
    val pathToPackage: String = "",
    /**
     *
     */
    @NestedConfigurationProperty
    val health: HealthEndpointProperties = HealthEndpointProperties()
)

class HealthEndpointProperties(
    val endpoint: String = "healthk",
    val enabled: Boolean = true
)
