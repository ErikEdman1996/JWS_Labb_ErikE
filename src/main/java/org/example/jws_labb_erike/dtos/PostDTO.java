package org.example.jws_labb_erike.dtos;

/*TODO: använda PostDTO istället för Post, borde inte visa keycloak sub till användaren*/

public class PostDTO
{
    private String title;
    private String content;

    public PostDTO()
    {

    }

    public PostDTO(String title, String content)
    {
        this.title = title;
        this.content = content;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }
}
