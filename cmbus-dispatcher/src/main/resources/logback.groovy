import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.core.ConsoleAppender
import ch.qos.logback.core.rolling.RollingFileAppender

import static ch.qos.logback.classic.Level.DEBUG
import static ch.qos.logback.classic.Level.INFO
import ch.qos.logback.core.rolling.TimeBasedRollingPolicy

println "Hostname: $hostname"

appender("STDOUT", ConsoleAppender) {
  encoder(PatternLayoutEncoder) {
    pattern = "%-50(%d{HH:mm:ss.SSS}[%.-1level:%thread]%logger{0})| %msg%n"
  }
}

appender("ROLLING", RollingFileAppender) {
  encoder(PatternLayoutEncoder) {
    pattern = "%d{HH:mm:ss.SSS} [%thread] %-5level %logger{4} - %msg%n"
  }
  rollingPolicy(TimeBasedRollingPolicy) {
    FileNamePattern = "./target/log/cmbus.log.%d"
  }
}

logger("org.apache.camel", DEBUG,[])
logger("pl.pentacomp.cmbus.dispatcher", DEBUG, [])
logger("pl.pentacomp.cmbus.dispatcher.concurrent", DEBUG, [])
logger("pl.pentacomp.cmbus.dispatcher.CxfR", DEBUG, [])
logger("pl.pentacomp.cmbus.mm7", INFO, [])

root(DEBUG, ["STDOUT", "ROLLING"])