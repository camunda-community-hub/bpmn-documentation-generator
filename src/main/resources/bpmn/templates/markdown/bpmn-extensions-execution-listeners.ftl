<#macro listExecutionListeners listeners>

    <#if listeners?has_content>
###### Execution Listeners
|
        <#list listeners[0] as listenerKey, listenerValue>
            <#list listenerValue as key, value>
 ${key?capitalize} |
            </#list>
        </#list>
|-|-|-|
        <#list listeners as listener>
            <#list listener as listenerKey, listenerValue>
|
                <#list listenerValue as key, value>
 ${value} |
                </#list>
            </#list>
        </#list>
    <#else>
<@util.emptySection skip=skipEmptySections section="execution listeners defined" quote=false markdown=true/>
    </#if>

</#macro>