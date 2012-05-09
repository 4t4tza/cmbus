import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.core.ConsoleAppender

import static ch.qos.logback.classic.Level.DEBUG
import static ch.qos.logback.classic.Level.INFO
import static ch.qos.logback.classic.Level.WARN

println "Hostname: $hostname"

appender("STDOUT", ConsoleAppender) {
  encoder(PatternLayoutEncoder) {
    pattern = "%d{HH:mm:ss.SSS} [%thread] %-5level %logger{5} - %msg%n"
  }
}

logger("org.apache.camel",DEBUG, ["STDOUT"], true)
logger("pl.pentacomp.cmbus.dispatcher", DEBUG, ["STDOUT"], false)
logger("pl.pentacomp.cmbus.dispatcher.concurrent", DEBUG, ["STDOUT"], true)
logger("pl.pentacomp.cmbus.dispatcher.CxfR", DEBUG, ["STDOUT"], true)
logger("pl.pentacomp.cmbus.mm7", INFO, ["STDOUT"], false)

root(DEBUG, ["STDOUT"])