<#import "../bpmn-template-util.ftl" as util/>

<#macro listTaskDefinition taskDefinition>

<#if taskDefinition?has_content>
    <h6>Task Definition</h6>
    <table>
        <thead>
            <tr>
                <th>Key</th>
                <th>Value</th>
            </tr>
        </thead>
        <tbody>
            <#list taskDefinition as key, value>
                <tr>
                    <td>${key?capitalize}</td>
                    <td>${value}</td>
                </tr>
            </#list>
        </tbody>
    </table>
<#else>
    <@util.emptySection skip=skipEmptySections section="task definition" quote=false />
</#if>

</#macro>

