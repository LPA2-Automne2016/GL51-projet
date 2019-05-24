package gl51.project

import gl51.project.store.Product
import gl51.project.store.ProductStorage
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Delete
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Patch
import io.micronaut.http.annotation.Post

import javax.inject.Inject

import io.micronaut.http.HttpStatus


@Controller("/store/product")
class ProductController {

    @Inject
    ProductStorage storage

    @Get("/")
    List<Product> index() {
        storage.all()
    }

    @Get("/{id}")
    HttpResponse<Product> get(String id) {
        try {
            HttpResponse.ok(storage.getById(id))
        }
        catch(NoSuchElementException e) {
            HttpResponse.notFound()
        }
    }

    @Post("/")
    String create(@Body Product p) {
        storage.save(p)
    }

    @Patch("/{id}")
    HttpStatus update(String id, @Body Product p) {
        try {
            storage.update(id, p)
            HttpStatus.NO_CONTENT
        }
        catch(NoSuchElementException e){
            HttpStatus.NOT_FOUND
        }
    }

    @Delete("/{id}")
    HttpStatus delete(String id) {
        try {
            storage.delete(id)
            HttpStatus.OK
        }
        catch(NoSuchElementException e) {
            HttpStatus.NOT_FOUND
        }
    }
}