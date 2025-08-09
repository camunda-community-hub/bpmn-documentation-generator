<#macro listExecutionListeners listeners>

    <#if listeners?has_content>
        <details>
            <summary><h4>Execution Listeners</h4></summary>
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
        </details>
    <#else>
        <p>No execution listeners defined.</p>
    </#if>

</#macro>