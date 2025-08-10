<#macro listProperties properties>

<#if properties?has_content>
    <details>
        <summary><h4>Properties</h4></summary>
            <table>
                <thead>
                <#list properties[0] as propertyKey, propertyValue>
                    <tr>
                        <#list propertyValue as key, value>
                            <th>${key?capitalize}</th>
                        </#list>
                    </tr>
                </#list>
                </thead>
                <tbody>
                <#list properties as property>
                    <#list property as propertyKey, propertyValue>
                        <tr>
                            <#list propertyValue as key, value>
                                <td>${value}</td>
                            </#list>
                        </tr>
                    </#list>
                </#list>
                </tbody>
            </table>
    </details>
<#else>
    <@util.emptySection skip=skipEmptySections section="properties defined" quote=false markdown=false/>
</#if>

</#macro>