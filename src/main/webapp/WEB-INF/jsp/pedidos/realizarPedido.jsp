
<%@ include file="/WEB-INF/jsp/common/include_taglib.jsp" %>


<style>
    .remaining-content span {
        display:none;
    }
</style>
<br><br><br><br>
<!-- Warning Modal -->
<div id="warningModal" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header bg-danger">
                <h5 class="modal-title text-white">
                    <i class="fas fa-exclamation-triangle fa-sm"></i>&nbsp;&nbsp;&#161;Atenci&oacute;n&#33;
                </h5>
                <button class="close text-white" data-dismiss="modal">&times;</button>
            </div>
            <div class="modal-body">
                <p class="text-danger"></p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-danger btn-sm"
                        data-dismiss="modal">
                    <i class="fas fa-times-circle pr-2"></i>Aceptar
                </button>
            </div>
        </div>
    </div>
</div>

<!-- ./ Warning Modal -->

<!-- Info Modal -->
<div id="infoModal" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header bg-info">
                <h5 class="modal-title text-white">
                    <i class="fas fa-info fa-lg"></i>&nbsp;&nbsp;
                    Informaci&oacute;n</h5>

                <button class="close text-white" data-dismiss="modal">&times;</button>
            </div>
            <div class="modal-body">
                <p class="text-info"></p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary btn-sm"
                        data-dismiss="modal">
                    <i class="fas fa-times-circle pr-2"></i>Cancelar
                </button>
                <a class="btn btn-info btn-sm" role="button"
                   href='javascript:document.getElementById("pedidoForm").submit();'
                   id="aceptarInfo">Aceptar</a>
            </div>
        </div>
    </div>
</div>
<!-- ./ Info Modal -->


<c:if test="${!empty platos}">

<div class="container">
    <div class="row">
        <div class="col">
            <form id="pedidoForm" action="confirmarPedido.do" method="post">
            <div class="card">
                <div class="card-header">
                    <ul class="nav nav-tabs card-header-tabs" id="realizar-pedido" role="tablist">
                        <c:forEach items="${constantes}" var="constante" varStatus="status">
                            <li class="nav-item">
                                <a class='nav-link ${status.first?"active":""}' href="#${constante.literal}" role="tab"
                                   aria-controls="${constante.literal}"
                                   aria-selected='${status.first?"true":"false"}'>${constante.literal}</a>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
                <div class="card-body">
                    <h4 class="card-title text-primary">Nuestra Carta</h4>
                    <h6 class="card-subtitle mb-2 text-success">Selecciona un plato de cada pesta&ntilde;a</h6>


                    <div class="tab-content mt-3">
                        <c:forEach items="${constantes}" var="constante" varStatus="statusConstante">
                        <!--  ${constante.literal}   -->
                        <div class="tab-pane ${statusConstante.first?"active":""}" id="${constante.literal}" role="tabpanel">
                            <div class="row row-cols-1 row-cols-md-2 row-cols-lg-3">
                                <c:forEach items="${platos}" var="plato" varStatus="statusPlato">
                                    <c:if test="${(constante.valor eq plato.tipo) && (plato.activo > 0)}">
                                <div class="col p-4">
                                    <div class="card shadow">
                                        <img src="<spring:url value="/static/imagenes/${plato.nombreFoto}"/>" class="card-img-top" alt="${constante.atributo} ${plato.nombre}">
                                        <div class="card-body">
                                            <h5 class="card-title">${plato.nombre}</h5>
                                            <div class="card-text text-justify texto-recortado">${plato.descripcion}</div>
                                            <hr>
                                            <div class="row">
                                                <c:set var="precio"><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${plato.precio}" /></c:set>
                                                <div class="col text-left"><h4>${precio}&euro;</h4></div>
                                                <div class="col">
                                                    <div class="custom-control custom-radio text-right">
                                                        <input type="radio" id="${constante.atributo}${statusPlato.count}" name="${constante.literal}Radio"
                                                               onclick="realizaSumaYMarcaPlato(this, ${plato.idPlato})" value="${plato.precio}" class="custom-control-input">
                                                        <label class="custom-control-label" for="${constante.atributo}${statusPlato.count}">&iexcl;Me lo pido!</label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                    </c:if>
                                </c:forEach>
                                <input type="hidden" id="${constante.literal}Radio_subtotal" name="${constante.literal}Radio_subtotal">
                                <input type="hidden" id="${constante.literal}Radio_selected" name="${constante.literal}Radio_selected">
                            </div>
                        </div>
                        <!--  ./${constante.literal}   -->
                        </c:forEach>

                    </div>
                </div>
            </div>
                <input type="hidden" id="totalPedido" name="totalPedido">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
<%--                <input type="hidden" id="numPlatos" name="numPlatos">--%>
            </form>
        </div>
    </div>
    <div class="row">
        <div class="col">&nbsp;</div>
    </div>
    <div class="row">
        <div class="col text-left">
            <a href="javascript:confirmarPedido();" class="btn btn-success btn-sm"><i class="fas fa-check pr-2"></i>Confirmar pedido</a>
            <a href="javascript:empezarDeNuevo()" class="btn btn-danger btn-sm text-white"><i class="fas fa-trash-alt pr-2"></i>Volver a empezar</a>
        </div>
        <div class="col text-right"><h5>Total: <span id="total">0.00</span>&euro;</h5></div>
    </div>
</div>
</c:if>


<script>
    // ???IMPORTANTE!!! >>> El script de recorte de texto necesita la siguiente clase css para funcionar correctamente
    // .remaining-content span {
    //     display:none;
    // }
    
    
    
    // Código extraido de: http://jsfiddle.net/nijinvinodan/WtdEh/

    var showChar = 50;
    var ellipsestext = "...";
    var moretext = "Leer m&aacute;s";
    var lesstext = "Leer menos";

    $('.texto-recortado').each(function () {
        var content = $(this).html();
        if (content.length > showChar) {
            var show_content = content.substr(0, showChar);
            var hide_content = content.substr(showChar, content.length - showChar);
            var html = show_content + '<span class="moreelipses">' + ellipsestext + '</span><span class="remaining-content"><span>' + hide_content + '</span>&nbsp;&nbsp;<a href="" class="morelink">' + moretext + '</a></span>';
            $(this).html(html);
        }
    });

    $(".morelink").click(function () {
        if ($(this).hasClass("less")) {
            $(this).removeClass("less");
            $(this).html(moretext);
        } else {
            $(this).addClass("less");
            $(this).html(lesstext);
        }
        $(this).parent().prev().toggle();
        $(this).prev().toggle();
        return false;
    });


    $('#realizar-pedido a').on('click', function (e) {
        e.preventDefault()
        $(this).tab('show');
    });

    function empezarDeNuevo() {
        document.getElementById('pedidoForm').reset();
        <c:forEach items="${constantes}" var="constante">
        document.getElementById('${constante.literal}Radio_subtotal').value = "0.00";
        </c:forEach>


        document.getElementById('total').innerHTML = "0.00";
    }
    
    function realizaSumaYMarcaPlato(radioButton, idPlato) {
    	realizaSuma(radioButton, idPlato);
    	marcarPlato(radioButton, idPlato);
    }

    function realizaSuma(radioButton, idPlato) {
        let totalAPagar = "0.0";

        let campoHidden = document.getElementById(radioButton.name + "_subtotal");
        campoHidden.value = radioButton.value;

        <c:forEach items="${constantes}" var="constante">
        let subtotal${constante.literal} = document.getElementById('${constante.literal}Radio_subtotal').value;
        if (subtotal${constante.literal}.length > 0) {
            totalAPagar = parseFloat(totalAPagar) + parseFloat(subtotal${constante.literal});
        }
        </c:forEach>

        document.getElementById('total').innerHTML = totalAPagar.toFixed(2);
    }

    function marcarPlato(radioButton, idPlato) {
        document.getElementById(radioButton.name + '_selected').value = idPlato;
    }

    function confirmarPedido() {
        let importePedido = document.getElementById('total').innerHTML;
        let message =  '';

        document.getElementById('totalPedido').value = document.getElementById('total').innerHTML;
        //document.getElementById('numPlatos').value = getNumPlatos();

        if (importePedido === "0.00") {
            message = 'No ha seleccionado ningún plato.';
            $('#warningModal').find('.modal-body p').html(message);
            $('#warningModal').modal('show');
        } else {
            message = 'Se va a proceder a realizar el pedido. ¿Está de acuerdo?';
            $('#infoModal').find('.modal-body p').html(message);
            $('#infoModal').modal('show');        	
        }
    }

</script>
