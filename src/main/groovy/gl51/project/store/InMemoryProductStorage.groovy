package gl51.project.store

import groovy.transform.CompileStatic

import javax.inject.Singleton

@CompileStatic
@Singleton
class InMemoryProductStorage implements ProductStorage {
    private List<Product> savedProducts = []

    @Override
    String save(Product product) {
        product.id = UUID.randomUUID().toString()
        savedProducts += product
        product.id
    }

    @Override
    Product getById(String id) {
        def foundProduct = savedProducts.find { it.id == id }

        if (foundProduct == null)
            throw new NoSuchElementException("No product with id '$id' exists here.")
        else
            return foundProduct
    }

    @Override
    List<Product> all() {
        savedProducts
    }

    @Override
    void update(String id, Product updatedProduct) {
        def foundProductIndex = savedProducts.findIndexOf { it.id == id }

        if (foundProductIndex == -1)
            throw new NoSuchElementException("No product with id '$id' exists here.")

        updatedProduct.id = id
        savedProducts[foundProductIndex] = updatedProduct
    }

    @Override
    void delete(String id) {
        savedProducts.removeIf { it.id == id }
    }
}
