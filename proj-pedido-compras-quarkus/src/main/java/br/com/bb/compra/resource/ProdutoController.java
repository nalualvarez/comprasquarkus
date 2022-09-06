package br.com.bb.compra.resource;

import br.com.bb.compra.model.Produto;
import br.com.bb.compra.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/produtos")
@RequiredArgsConstructor
@Slf4j
public class ProdutoController {

    private final ProdutoService produtoService;

    @GET
    @Path("/{id}")
    public Response getId(@PathParam("id") Long id) {
        return Response.ok(produtoService.buscaPorId(id)).build();
    }

    @POST
    @RolesAllowed("ADMIN")
    public Response salvar(
            @HeaderParam("Authorization") String token,
            @Valid Produto produto) {
        return Response.ok(produtoService.salvar(produto)).build();
    }

    @GET
    public Response listar(@QueryParam("filtro") @DefaultValue("") String filtro,
                           @QueryParam("page") @DefaultValue("0") Integer page,
                           @QueryParam("size") @DefaultValue("20") Integer size) {
        log.info("Recebendo filtro {}", filtro);
        return Response.ok(produtoService.listar(filtro, page, size)).build();
    }

    @DELETE

    @Path("/{id}")

    public Response excluirProduto(@PathParam("id") Long id) {



        return Response.ok(produtoService.excluir(id)).build();

    }



}
