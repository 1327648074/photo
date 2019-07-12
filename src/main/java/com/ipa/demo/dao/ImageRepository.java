//作者：陈志漂
package com.ipa.demo.dao;

import com.ipa.demo.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ImageRepository extends JpaRepository<Image, Integer> {
    public List<Image> findByName(String name);

    public List<Image> findByUrl(String url);

    public Image findByNameAndUrl(String name, String url);

    public void deleteByName(String name);

}
