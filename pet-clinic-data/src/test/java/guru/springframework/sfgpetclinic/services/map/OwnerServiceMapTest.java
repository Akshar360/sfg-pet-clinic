package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OwnerServiceMapTest
{
    OwnerServiceMap ownerServiceMap;
    final Long ownerId = 1L;
    final String lastName = "Schumacher";

    @BeforeEach
    void setUp()
    {
        ownerServiceMap = new OwnerServiceMap(new PetTypeServiceMap(),new PetServiceMap());
        ownerServiceMap.save(Owner.builder().id(ownerId).lastName(lastName).build()); //build an owner
    }

    @Test
    void findAll()
    {
        Set<Owner> ownerSet = ownerServiceMap.findAll();
        assertEquals(1,ownerSet.size());
    }

    @Test
    void findByID()
    {
        Owner ownerFound = ownerServiceMap.findByID(ownerId);
        assertEquals(ownerId,ownerFound.getId());
    }

    @Test
    void saveExistingId()
    {
        Long id = 2L;
        Owner owner2 = Owner.builder().id(id).build();
        Owner savedOwner = ownerServiceMap.save(owner2);
        assertEquals(id,savedOwner.getId());
    }

    @Test
    void saveNoId()
    {
        Owner savedOwner =  ownerServiceMap.save(Owner.builder().build());
        assertNotNull(savedOwner);
        assertNotNull(savedOwner.getId());
    }

    @Test
    void delete() //before always runs before each test
    {
        ownerServiceMap.delete(ownerServiceMap.findByID(ownerId));

        assertEquals(0,ownerServiceMap.findAll().size());
    }

    @Test
    void deleteById()
    {
        ownerServiceMap.deleteById(ownerId);
        assertEquals(0,ownerServiceMap.findAll().size());
    }

    @Test
    void findByLastName()
    {
        Owner foundOwner = ownerServiceMap.findByLastName(lastName);
        assertNotNull(foundOwner);
        assertEquals(ownerId,foundOwner.getId());
    }
    void findByLastNameNotFound()
    {
        Owner foundOwner = ownerServiceMap.findByLastName("test");
        assertNull(foundOwner);
    }

}