import React, { useState, useEffect } from 'react';

function App() {
  const [activeTab, setActiveTab] = useState('anamneza');

  // Stări formulare
  const [sectieData, setSectieData] = useState({ nume: '', sefSectie: '' });
  const [angajatData, setAngajatData] = useState({ nume: '', prenume: '', cnp: '', functie: 'Medic Specialist', salariu: '', sectie: '' });
  const [pacientData, setPacientData] = useState({ nume: '', prenume: '', cnp: '', varsta: '', diagnostic: '' });
  const [anamnezaData, setAnamnezaData] = useState({ cnpPacient: '', simptome: '', istoricMedical: '', diagnosticPrincipal: '', medicatiePrescrisa: '' });

  // Stări rapoarte
  const [raportSectii, setRaportSectii] = useState([]);
  const [raportAngajati, setRaportAngajati] = useState([]);
  const [raportPacienti, setRaportPacienti] = useState([]);
  const [raportAnamneze, setRaportAnamneze] = useState([]);

  // Funcție de încărcare rapoarte
  const incarcaRapoarte = async () => {
    try {
      const resSectii = await fetch('http://localhost:8080/api/raport/sectii');
      setRaportSectii(await resSectii.json());

      const resAngajati = await fetch('http://localhost:8080/api/raport/angajati');
      setRaportAngajati(await resAngajati.json());

      const resPacienti = await fetch('http://localhost:8080/api/raport/pacienti');
      setRaportPacienti(await resPacienti.json());

      const resAnamneze = await fetch('http://localhost:8080/api/raport/anamneze');
      setRaportAnamneze(await resAnamneze.json());
    } catch (err) {
      console.error("Eroare la preluarea rapoartelor:", err);
    }
  };

  useEffect(() => {
    incarcaRapoarte();
  }, [activeTab]);

  // Funcții trimitere date spre Java
  const handleSubmitSectie = async (e) => {
    e.preventDefault();
    await fetch('http://localhost:8080/api/sectie', { method: 'POST', body: JSON.stringify(sectieData) });
    alert('Secție salvată!');
    setSectieData({ nume: '', sefSectie: '' });
    incarcaRapoarte();
  };

  const handleSubmitAngajat = async (e) => {
    e.preventDefault();
    await fetch('http://localhost:8080/api/angajat', { method: 'POST', body: JSON.stringify(angajatData) });
    alert('Angajat înregistrat!');
    setAngajatData({ nume: '', prenume: '', cnp: '', functie: 'Medic Specialist', salariu: '', sectie: '' });
    incarcaRapoarte();
  };

  const handleSubmitPacient = async (e) => {
    e.preventDefault();
    await fetch('http://localhost:8080/api/pacient', { method: 'POST', body: JSON.stringify(pacientData) });
    alert('Pacient înregistrat!');
    setPacientData({ nume: '', prenume: '', cnp: '', varsta: '', diagnostic: '' });
    incarcaRapoarte();
  };

  const handleSubmitAnamneza = async (e) => {
    e.preventDefault();
    await fetch('http://localhost:8080/api/anamneza', { method: 'POST', body: JSON.stringify(anamnezaData) });
    alert('Anamneză salvată!');
    setAnamnezaData({ cnpPacient: '', simptome: '', istoricMedical: '', diagnosticPrincipal: '', medicatiePrescrisa: '' });
    incarcaRapoarte();
  };

  // Funcții ștergere
  const stergeSectie = async (nume) => {
    await fetch('http://localhost:8080/api/sterge/sectie', { method: 'POST', body: JSON.stringify({ nume }) });
    incarcaRapoarte();
  };

  const stergeAngajat = async (cnp) => {
    await fetch('http://localhost:8080/api/sterge/angajat', { method: 'POST', body: JSON.stringify({ cnp }) });
    incarcaRapoarte();
  };

  const stergePacient = async (cnp) => {
    await fetch('http://localhost:8080/api/sterge/pacient', { method: 'POST', body: JSON.stringify({ cnp }) });
    incarcaRapoarte();
  };

  const stergeAnamneza = async (cnp) => {
    await fetch('http://localhost:8080/api/sterge/anamneza', { method: 'POST', body: JSON.stringify({ cnp }) });
    incarcaRapoarte();
  };

  return (
      <div style={styles.container}>
        <header style={styles.header}>
          <h1>🏥 Sistem Gestiune Spital & Arhivă CSV</h1>
          <p>Proiect POO & React - Complet cu Rapoarte și Ștergere</p>
        </header>

        <nav style={styles.nav}>
          <button style={activeTab === 'sectii' ? {...styles.button, ...styles.activeButton} : styles.button} onClick={() => setActiveTab('sectii')}>📂 Secții</button>
          <button style={activeTab === 'angajati' ? {...styles.button, ...styles.activeButton} : styles.button} onClick={() => setActiveTab('angajati')}>👨‍⚕️ Angajați</button>
          <button style={activeTab === 'pacienti' ? {...styles.button, ...styles.activeButton} : styles.button} onClick={() => setActiveTab('pacienti')}>🛏️ Pacienți</button>
          <button style={activeTab === 'anamneza' ? {...styles.button, ...styles.activeButton} : styles.button} onClick={() => setActiveTab('anamneza')}>📋 Anamneză</button>
        </nav>

        <main style={styles.mainContent}>

          {/* SECȚII */}
          {activeTab === 'sectii' && (
              <div style={styles.card}>
                <h2>Gestionare Secții</h2>
                <form onSubmit={handleSubmitSectie} style={styles.form}>
                  <input type="text" placeholder="Nume Secție" value={sectieData.nume} onChange={e => setSectieData({...sectieData, nume: e.target.value})} style={styles.input} required />
                  <input type="text" placeholder="Șef Secție" value={sectieData.sefSectie} onChange={e => setSectieData({...sectieData, sefSectie: e.target.value})} style={styles.input} required />
                  <button type="submit" style={styles.submitButton}>Salvează Secția</button>
                </form>
                <h3>📊 Raport Secții Existente:</h3>
                <ul style={styles.list}>
                  {raportSectii.map((item, idx) => (
                      <li key={idx} style={styles.listItem}>
                        <span>{item}</span>
                        <button onClick={() => stergeSectie(item.split(',')[0])} style={styles.deleteButton}>Șterge</button>
                      </li>
                  ))}
                </ul>
              </div>
          )}

          {/* ANGAJAȚI */}
          {activeTab === 'angajati' && (
              <div style={styles.card}>
                <h2>Angajare Personal</h2>
                <form onSubmit={handleSubmitAngajat} style={styles.form}>
                  <input type="text" placeholder="Nume" value={angajatData.nume} onChange={e => setAngajatData({...angajatData, nume: e.target.value})} style={styles.input} required />
                  <input type="text" placeholder="Prenume" value={angajatData.prenume} onChange={e => setAngajatData({...angajatData, prenume: e.target.value})} style={styles.input} required />
                  <input type="text" placeholder="CNP" value={angajatData.cnp} onChange={e => setAngajatData({...angajatData, cnp: e.target.value})} style={styles.input} required />
                  <select value={angajatData.functie} onChange={e => setAngajatData({...angajatData, functie: e.target.value})} style={styles.input}>
                    <option value="Medic Primar">Medic Primar</option>
                    <option value="Medic Specialist">Medic Specialist</option>
                    <option value="Medic Rezident">Medic Rezident</option>
                    <option value="Asistent">Asistent</option>
                    <option value="Infirmier">Infirmier</option>
                    <option value="Brancardier">Brancardier</option>
                    <option value="Manager">Manager</option>
                  </select>
                  <input type="number" placeholder="Salariu" value={angajatData.salariu} onChange={e => setAngajatData({...angajatData, salariu: e.target.value})} style={styles.input} required />
                  <input type="text" placeholder="Secție" value={angajatData.sectie} onChange={e => setAngajatData({...angajatData, sectie: e.target.value})} style={styles.input} required />
                  <button type="submit" style={styles.submitButton}>Înregistrează Angajat</button>
                </form>
                <h3>📊 Raport Angajați:</h3>
                <ul style={styles.list}>
                  {raportAngajati.map((item, idx) => (
                      <li key={idx} style={styles.listItem}>
                        <span>{item}</span>
                        <button onClick={() => stergeAngajat(item.split(',')[2])} style={styles.deleteButton}>Șterge</button>
                      </li>
                  ))}
                </ul>
              </div>
          )}

          {/* PACIENȚI */}
          {activeTab === 'pacienti' && (
              <div style={styles.card}>
                <h2>Primire Pacienți</h2>
                <form onSubmit={handleSubmitPacient} style={styles.form}>
                  <input type="text" placeholder="Nume" value={pacientData.nume} onChange={e => setPacientData({...pacientData, nume: e.target.value})} style={styles.input} required />
                  <input type="text" placeholder="Prenume" value={pacientData.prenume} onChange={e => setPacientData({...pacientData, prenume: e.target.value})} style={styles.input} required />
                  <input type="text" placeholder="CNP" value={pacientData.cnp} onChange={e => setPacientData({...pacientData, cnp: e.target.value})} style={styles.input} required />
                  <input type="number" placeholder="Vârstă" value={pacientData.varsta} onChange={e => setPacientData({...pacientData, varsta: e.target.value})} style={styles.input} required />
                  <input type="text" placeholder="Diagnostic Preliminar" value={pacientData.diagnostic} onChange={e => setPacientData({...pacientData, diagnostic: e.target.value})} style={styles.input} required />
                  <button type="submit" style={styles.submitButton}>Adaugă Pacient</button>
                </form>
                <h3>📊 Raport Pacienți:</h3>
                <ul style={styles.list}>
                  {raportPacienti.map((item, idx) => (
                      <li key={idx} style={styles.listItem}>
                        <span>{item}</span>
                        <button onClick={() => stergePacient(item.split(',')[2])} style={styles.deleteButton}>Șterge</button>
                      </li>
                  ))}
                </ul>
              </div>
          )}

          {/* ANAMNEZĂ */}
          {activeTab === 'anamneza' && (
              <div style={styles.card}>
                <h2>📋 Formular Anamneză & Medicație</h2>
                <form onSubmit={handleSubmitAnamneza} style={styles.form}>
                  <input type="text" placeholder="CNP Pacient" value={anamnezaData.cnpPacient} onChange={e => setAnamnezaData({...anamnezaData, cnpPacient: e.target.value})} style={styles.input} required />
                  <textarea placeholder="Simptome..." rows="2" value={anamnezaData.simptome} onChange={e => setAnamnezaData({...anamnezaData, simptome: e.target.value})} style={styles.textarea} required />
                  <textarea placeholder="Istoric medical..." rows="2" value={anamnezaData.istoricMedical} onChange={e => setAnamnezaData({...anamnezaData, istoricMedical: e.target.value})} style={styles.textarea} />
                  <input type="text" placeholder="Diagnostic Principal" value={anamnezaData.diagnosticPrincipal} onChange={e => setAnamnezaData({...anamnezaData, diagnosticPrincipal: e.target.value})} style={styles.input} required />
                  <textarea placeholder="Medicație prescrisă..." rows="2" value={anamnezaData.medicatiePrescrisa} onChange={e => setAnamnezaData({...anamnezaData, medicatiePrescrisa: e.target.value})} style={styles.textarea} required />
                  <button type="submit" style={{...styles.submitButton, backgroundColor: '#28a745'}}>Salvează Anamneza</button>
                </form>
                <h3>📊 Raport Anamneze:</h3>
                <ul style={styles.list}>
                  {raportAnamneze.map((item, idx) => (
                      <li key={idx} style={styles.listItem}>
                        <span>{item}</span>
                        <button onClick={() => stergeAnamneza(item.split(',')[0])} style={styles.deleteButton}>Șterge</button>
                      </li>
                  ))}
                </ul>
              </div>
          )}

        </main>
      </div>
  );
}

const styles = {
  container: { fontFamily: 'Segoe UI, sans-serif', maxWidth: '900px', margin: '0 auto', padding: '20px', backgroundColor: '#f8f9fa', minHeight: '100vh' },
  header: { textAlign: 'center', marginBottom: '20px', color: '#333' },
  nav: { display: 'flex', justifyContent: 'center', gap: '10px', marginBottom: '30px', flexWrap: 'wrap' },
  button: { padding: '10px 18px', fontSize: '15px', cursor: 'pointer', backgroundColor: '#e2e8f0', border: 'none', borderRadius: '6px', fontWeight: 'bold', color: '#4a5568' },
  activeButton: { backgroundColor: '#007bff', color: '#fff' },
  mainContent: { display: 'flex', justifyContent: 'center' },
  card: { backgroundColor: '#fff', padding: '30px', borderRadius: '10px', boxShadow: '0 4px 12px rgba(0,0,0,0.1)', width: '100%' },
  form: { display: 'flex', flexDirection: 'column', gap: '12px', marginBottom: '20px' },
  input: { padding: '10px', borderRadius: '5px', border: '1px solid #ccc', fontSize: '15px', width: '100%', boxSizing: 'border-box' },
  textarea: { padding: '10px', borderRadius: '5px', border: '1px solid #ccc', fontSize: '15px', width: '100%', boxSizing: 'border-box' },
  submitButton: { padding: '12px', backgroundColor: '#007bff', color: '#fff', border: 'none', borderRadius: '6px', fontSize: '16px', fontWeight: 'bold', cursor: 'pointer' },
  list: { listStyleType: 'none', padding: 0 },
  listItem: { display: 'flex', justifyContent: 'space-between', alignItems: 'center', background: '#f1f5f9', padding: '10px', margin: '6px 0', borderRadius: '5px', fontSize: '14px' },
  deleteButton: { backgroundColor: '#dc3545', color: '#fff', border: 'none', padding: '5px 10px', borderRadius: '4px', cursor: 'pointer', fontWeight: 'bold' }
};

export default App;