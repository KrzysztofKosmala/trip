package pl.kosmala.shop.common.utils;

import com.github.slugify.Slugify;

public class SlugifyUtils
{
    public static String slugifySlug(String slug)
    {
        final Slugify slg = Slugify.builder().customReplacement("_", "-").build();
        return slg.slugify(slug);
    }
}
