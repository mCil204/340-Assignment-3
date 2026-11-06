<#import "layout.ftlh" as l>
<#assign specialties = ["Tremor", "Rupture", "Bleed", "Burn", "Poise", "Charge", "Sinking", "Heartbreak"]>
<#assign factions = ["N. Corp", "LCB", "The Middle", "Liu Association", "Molar Boatworks"]>
<@l.page title=formTitle active="new" mainClass="character-form-page">
    <h1>${formTitle}</h1>

    <form class="character-form" method="post" action="${formAction}">
        <label for="name">Character Name</label>
        <input type="text" id="name" name="name" value="${character.name!}"
               placeholder="Enter character name" required>

        <label for="specialty">Specialty / Attribute</label>
        <select id="specialty" name="specialty">
            <option value="">Choose an attribute</option>
            <#list specialties as s>
                <option value="${s}" <#if (character.specialty!'') == s>selected</#if>>${s}</option>
            </#list>
        </select>

        <label for="faction">Faction</label>
        <select id="faction" name="faction">
            <option value="">Choose a faction</option>
            <#list factions as f>
                <option value="${f}" <#if (character.faction!'') == f>selected</#if>>${f}</option>
            </#list>
        </select>

        <label for="imageUrl">Image URL</label>
        <input type="text" id="imageUrl" name="imageUrl" value="${character.imageUrl!}"
               placeholder="/images/yi_sang.jpg">

        <label for="description">Short Description</label>
        <textarea id="description" name="description" rows="5"
                  placeholder="Write a short description..." required>${character.description!}</textarea>

        <button type="submit">Save Character</button>
    </form>
</@l.page>