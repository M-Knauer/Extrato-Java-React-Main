import "./style.css";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import { useEffect, useState } from "react";
import axios from "axios";
import { BASE_URL } from "../../utils/request";
import { format, parseISO } from "date-fns";
import { Transferencia } from "../../models/transferencia";


function TransferenciasCard() {

    const max = new Date();
    const min = new Date(new Date().setDate(new Date().getDate() - (5 * 365)));

    const [minDate, setMinDate] = useState(min);
    const [maxDate, setMaxDate] = useState(max);

    const [nome, setNome] = useState(String);
    const [id, setId] = useState(String);

    const [transferencias, setTransferencias] = useState<Transferencia[]>([])

    useEffect(() => {
        const dmin = minDate.toISOString().slice(0, 10);
        const dmax = maxDate.toISOString().slice(0, 10);
        
        axios.get(`${BASE_URL}/transferencia/conta?minDate=${dmin}&maxDate=${dmax}&id=${id}&nome=${nome}`)
            .then(response => {
                setTransferencias(response.data.content)
            })   
    }, [minDate, maxDate, id, nome])

    return (
        <div className="dsmeta-card">
            <h2 className="dsmeta-sales-title">Transferencias</h2>
            <div>
                <div className="dsmeta-form-control-container">
                    <DatePicker
                        selected={minDate}
                        onChange={(date: Date) => setMinDate(date)}
                        className="dsmeta-form-control"
                        dateFormat="dd/MM/yyyy"
                    />
                </div>
                <div className="dsmeta-form-control-container">
                    <DatePicker
                        selected={maxDate}
                        onChange={(date: Date) => setMaxDate(date)}
                        className="dsmeta-form-control"
                        dateFormat="dd/MM/yyyy"
                    />
                </div>

                <div className="dsmeta-form-control-container">
                    <input 
                    type="text" 
                    placeholder="Nome"
                    className="dsmeta-form-control"
                    id="fieldName"  
                    onChange={(e) => setNome(e.target.value)}
                    />
                </div>

                <div className="dsmeta-form-control-container">
                    <input 
                    type="number" 
                    placeholder="Id"
                    id="fieldId"
                    className="dsmeta-form-control"  
                    onChange={(e) => setId(e.target.value)}
                    />
                </div>
            </div>

            <div>
                <table className="dsmeta-sales-table">
                    <thead>
                        <tr>
                            <th className="show576">Dados</th>
                            <th className="show576">Valentia</th>
                            <th className="show576">Tipo</th>
                            <th className="show576">Nome operador<br/>Transacionado</th>
                        </tr>
                    </thead>
                    <tbody>
                        {transferencias.map(tr => {
                            return (
                                <tr key={tr.id}>
                                    <td className="show576">{format(parseISO(tr.dataTransferencia), "dd/MM/yyyy")}</td>
                                    <td>R$ {tr.valor.toFixed(2)}</td>
                                    <td className="show992">{tr.tipo}</td>
                                    <td className="show992">{tr.nome}</td>
                                </tr>
                            )
                        })}
                    </tbody>

                </table>
            </div>

        </div>
    )
}

export default TransferenciasCard;
