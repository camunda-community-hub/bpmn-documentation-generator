<#macro listInput input>

<#if input?has_content>
    <details>
        <summary><h4>Input</h4></summary>
        <ul>
            <#list input as key, value>
            <li><strong>${key}:</strong> ${value}</li>
            </#list>
        </ul>
    </details>
<#else>
    <p>No input defined.</p>
</#if>

</#macro>