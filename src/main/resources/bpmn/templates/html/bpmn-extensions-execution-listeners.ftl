<#macro listExecutionListeners listeners>

    <#if listeners?has_content>
        <h6>Execution Listeners</h6>
        <table>
            <thead>
                <#list listeners[0] as listenerKey, listenerValue>
                    <tr>
                        <#list listenerValue as key, value>
                            <th>${key?capitalize}</th>
                        </#list>
                    </tr>
                </#list>
            </thead>
            <tbody>
            <#list listeners as listener>
                <#list listener as listenerKey, listenerValue>
                    <tr>
                        <#list listenerValue as key, value>
                            <td>${value}</td>
                        </#list>
                    </tr>
                </#list>
            </#list>
            </tbody>
        </table>
    <#else>
        <@util.emptySection skip=skipEmptySections section="execution listeners defined" quote=false />
    </#if>

</#macro>