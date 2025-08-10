<#macro listHeader headers>

    <#if headers?has_content>
        <details>
            <summary><h4>Headers</h4></summary>
            <table>
                <thead>
                <#list headers[0] as headerKey, headerValue>
                    <tr>
                        <#list headerValue as key, value>
                            <th>${key?capitalize}</th>
                        </#list>
                    </tr>
                </#list>
                </thead>
                <tbody>
                <#list headers as header>
                    <#list header as headerKey, headerValue>
                        <tr>
                            <#list headerValue as key, value>
                                <td>${value}</td>
                            </#list>
                        </tr>
                    </#list>
                </#list>
                </tbody>
            </table>
        </details>
    <#else>
        <@util.emptySection skip=skipEmptySections section="headers defined" quote=false markdown=false/>
    </#if>

</#macro>