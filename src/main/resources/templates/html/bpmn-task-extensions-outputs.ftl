<#macro listOutput output>

<#if output?has_content>
    <details>
        <summary><h4>Output</h4></summary>
        <ul>
            <#list output as key, value>
            <li><strong>${key}:</strong> ${value}</li>
            </#list>
        </ul>
    </details>
<#else>
    <p>No output defined.</p>
</#if>

</#macro>