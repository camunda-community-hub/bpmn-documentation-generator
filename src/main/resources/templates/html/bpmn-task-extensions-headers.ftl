<#macro listHeader header>

<#if header?has_content>
    <details>
        <summary><h4>Task Headers</h4></summary>
        <ul>
            <#list header as key, value>
            <li><strong>${key}:</strong> ${value}</li>
            </#list>
        </ul>
    </details>
<#else>
    <p>No header defined.</p>
</#if>

</#macro>