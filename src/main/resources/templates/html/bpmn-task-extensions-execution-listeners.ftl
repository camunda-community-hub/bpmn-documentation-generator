<#macro listExecutionListeners listeners>

    <#if listeners?has_content>
        <details>
            <summary><h4>Execution Listeners</h4></summary>
            <ul>
                <#list listeners as key, value>
                    <li><strong>${key}:</strong> ${value}</li>
                </#list>
            </ul>
        </details>
    <#else>
        <p>No listeners defined.</p>
    </#if>

</#macro>