package io.alienhead.kleuth.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import org.springframework.boot.context.properties.NestedConfigurationProperty

/**
 * Configuration properties needed to operate Kleuth.
 *
 * @param pathToRoot the path to the root package of Kleuth Routes and Route Controllers.
 *                   Example: if the routes start in the api package at "com/example/demo/api"
 *                            the pathToRoot property should be "com/example/demo/api".
 *                            Otherwise every url will begin with "com/example/demo/api"
 */
@ConfigurationProperties(prefix = "kleuth.core")
@ConstructorBinding
class KleuthProperties(val pathToRoot: String = "", )
