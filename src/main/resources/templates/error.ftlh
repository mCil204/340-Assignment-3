<#import "layout.ftlh" as l>
<@l.page title="Not Found">
    <h1>Character not found</h1>
    <p>${errorMessage!"That character doesn't exist."}</p>
    <a class="button" href="/">Back to the gallery</a>
</@l.page>