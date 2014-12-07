package info.keik.bookmanager.service;

import info.keik.bookmanager.domain.ItemsRepository;
import info.keik.bookmanager.domain.TagsRepository;
import info.keik.bookmanager.model.Item;
import info.keik.bookmanager.model.Tag;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagsServiceImpl implements TagsService {

    @Autowired
    private ItemsRepository itemsRepository;

    @Autowired
    private TagsRepository tagsRepository;

    @Override
    public List<Tag> findAllTags() {
        return tagsRepository.findAll();
    }

    @Override
    public Tag addTagToItem(Integer itemId, Tag tag) {
        Tag t = tagsRepository.findByName(tag.getName());
        Item item = itemsRepository.findOne(itemId);

        if (t == null) {

            // Create a new tag
            t = tag;
            t.addItem(item);
            return tagsRepository.save(t);
        }

        if (t.getItems().contains(item)) {

            // Already a book is tagged, nothing to update
            return null;
        } else {

            // Add a tag to a book
            t.addItem(item);
            return tagsRepository.save(t);
        }
    }

    @Override
    public Boolean deleteTagFromItem(Integer itemId, Integer tagId) {
        Tag tag = tagsRepository.findOne(tagId);
        Item item = itemsRepository.findOne(itemId);

        if (tag == null) {

            // No tag to delete
            return false;
        }

        if (!tag.getItems().contains(item)) {

            // A book isn't tagged, nothing to update
            return false;
        } else {

            // Delete a tag from a book
            tag.removeItem(item);
            tagsRepository.save(tag);
            return true;
        }
    }
}
