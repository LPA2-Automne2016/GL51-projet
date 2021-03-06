package gl51.project.store

interface ProductStorage {

    /**
     * creates an new product in the store
     * @param p the product to store
     * @return the ID of the created object
     */
    String save(Product p)

    /**
     * updates an existing product in the store
     * Beware the product id must be filled in
     * @param p the product to update
     */
    void update(String id, Product p)

    /**
     * get a product by its id
     * @param id
     * @return a product
     */
    Product getById(String id)

    /**
     * deletes a product by its id
     * @param id
     */
    void delete(String id)

    /**
     * list all products
     * @return a list of products
     */
    List<Product> all()
}
